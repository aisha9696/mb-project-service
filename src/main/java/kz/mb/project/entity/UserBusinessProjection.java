package kz.mb.project.entity;

import java.util.UUID;

import org.springframework.data.rest.core.config.Projection;

import kz.mb.project.auth.entity.UserDetail;

@Projection(name = "fullUserBusiness", types = { UserBusiness.class })
public interface UserBusinessProjection {
  UUID getId();
  UserDetail getUser();
  Business getBusiness();
  UserRole getUserRoles();
}