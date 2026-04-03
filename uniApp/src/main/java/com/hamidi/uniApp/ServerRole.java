package com.hamidi.uniApp;

public enum ServerRole {
    OWNER, EDITOR, CONTROLLER, MEMBER;
    public boolean checkRole(ServerRole role){
        return this.name().equals(role.name());
    }
}
