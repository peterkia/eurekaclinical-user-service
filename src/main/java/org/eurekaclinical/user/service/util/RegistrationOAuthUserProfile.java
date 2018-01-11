/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.eurekaclinical.user.service.util;

/*-
 * #%L
 * Eureka! Clinical User Service
 * %%
 * Copyright (C) 2016 - 2017 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

/**
 *
 * @author mai
 */
public class RegistrationOAuthUserProfile {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String providerUsername;
    private String provider;
    
    public void setFirstName(String value){
        this.firstName = value;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public void setLastName(String value){
        this.lastName = value;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public void setUserName(String value){
        this.userName = value;
    }
    
    public String getUserName(){
        return this.userName;
    }
    
    public void setEmail(String value){
        this.email = value;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setProviderUsername(String value){
        this.providerUsername = value;
    }
    
    public String getProviderUsername(){
        return this.providerUsername;
    }
    
    public void setProvider(String value){
        this.provider = value;
    }
    
    public String getProvider(){
        return this.provider;
    }
}
