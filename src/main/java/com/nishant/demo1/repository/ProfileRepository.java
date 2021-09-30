package com.nishant.demo1.repository;

import com.nishant.demo1.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long>  {
}
