package com.jairoguo.infra.common.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Guo Jinru
 */
public abstract class BaseEntity implements Serializable {

  protected LocalDateTime createTime;
  protected LocalDateTime updateTime;

  protected String createUser;
  protected String updateUser;

  protected boolean deleted;

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  public String getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }
}
