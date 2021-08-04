package com.example.microservicessteps.web;

import com.example.microservicessteps.exception.UserNotFoundException;
import com.example.microservicessteps.model.AppUser;
import com.example.microservicessteps.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AppUserResource {

    private final UserServiceImpl userService;

    @GetMapping
    public List<AppUser> getAll() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<AppUser>> getOne(@PathVariable Integer id) {
        AppUser findedUser = userService.getById(id);
        if (findedUser != null) {
            EntityModel<AppUser> entityModel = new EntityModel<>(findedUser);
            //забрать адресс с метода контроллера
            WebMvcLinkBuilder linkTo = linkTo(
                    methodOn(this.getClass())
                            .getAll()
            );
            //добавить в ответ с названием поля в json all-users
            entityModel.add(linkTo.withRel("all-users"));

            return ResponseEntity.ok(entityModel);
        }
        throw new UserNotFoundException(String.format("Пользователь %d не существует", id));
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody AppUser user) {
        AppUser savedUser = userService.save(user);
        if (savedUser != null) {
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppUser> deleteUser(@PathVariable Integer id) {
        AppUser user = userService.delete(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }

        throw new UserNotFoundException("Пользователь которого вы хотите удалить не существует");
    }
}
