/*
 * Copyright 2018-2018 adorsys GmbH & Co KG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.adorsys.ledgers.um.db.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "login", name = "user_login_unique"),
        @UniqueConstraint(columnNames = "email", name = "user_email_unique")
})
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private String id;

    @NotNull
    @Column(nullable = false)
    private String login;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String pin;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ScaUserData> scaMethods = new ArrayList<ScaUserData>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AccountAccess> accountAccesses = new ArrayList<AccountAccess>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<ScaUserData> getScaMethods() {
        return scaMethods;
    }

    public void setScaMethods(List<ScaUserData> scaMethods) {
        this.scaMethods = scaMethods;
    }

    public List<AccountAccess> getAccountAccesses() {
        return accountAccesses;
    }

    public void setAccountAccesses(List<AccountAccess> accountAccesses) {
        this.accountAccesses = accountAccesses;
    }
}