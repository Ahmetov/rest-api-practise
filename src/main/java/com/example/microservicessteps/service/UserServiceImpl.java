package com.example.microservicessteps.service;

import com.example.microservicessteps.model.AppUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl {
    private static ArrayList<AppUser> users = new ArrayList<>();

    private static int usersCount = 4;

    static {
        users.add(new AppUser(1, "Emil", new Date(2020, 2, 20)));
        users.add(new AppUser(2, "Kamil",  new Date(2011, 10, 20, 20, 20)));
        users.add(new AppUser(3, "Shamil",  new Date(2010, 8, 20, 20, 20)));
        users.add(new AppUser(4, "Nail",  new Date(2015, 5, 20, 20, 20)));
    }

    public List<AppUser> getUsers() {
        return users;
    }

    public AppUser save(AppUser user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        } else if (user.getId() != usersCount + 1) {
            return null; //TODO throw error
        }
        users.add(user);
        return user;
    }

    public AppUser getById(Integer id) {
        for (AppUser u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public AppUser delete(Integer id) {
        Iterator<AppUser> users = this.getUsers().iterator();
        while (users.hasNext()) {
            AppUser user = users.next();
            if (user.getId().equals(id)) {
                users.remove();
                return user;
            }
        }
        return null;
    }
}
