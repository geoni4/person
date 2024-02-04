package com.cos.person.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.person.domain.CommonDto;
import com.cos.person.domain.JoinReqDto;
import com.cos.person.domain.UpdateReqDto;
import com.cos.person.domain.User;
import com.cos.person.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
  
  private final UserRepository userRepository;

  @GetMapping("/user")
  public List<User> findAll(){
    System.out.println("findAll()");
    return userRepository.findAll(); // MessageConverter (JavaObject -> Json String)
  }
  
  @GetMapping("/user/{id}")
  public User findById(@PathVariable int id){
    System.out.println("findById() id: " +id);
    return userRepository.findById(id);
    
  }
  
  @PostMapping("/user")
  // x-xxx-form-urlencoded => request.getParameter()
  // text/plain => @RequestBody
  // application/json => @RequestBody + Object로 받기
  public CommonDto<?> save(@Valid @RequestBody JoinReqDto dto, BindingResult bindingResult){

    System.out.println("save()");
    System.out.println(dto);
    userRepository.save(dto);
    return new CommonDto<>(HttpStatus.OK.value(), "ok");
  }
  
  @DeleteMapping("/user/{id}")
  public CommonDto delete(@PathVariable int id){
    System.out.println("delete()");
    return new CommonDto<>(HttpStatus.OK.value());
  }
  
  @PutMapping("/user/{id}")
  public CommonDto<?> update(@PathVariable int id,@Valid @RequestBody UpdateReqDto dto, BindingResult bindingResult){


    System.out.println("update()");
    userRepository.update(id, dto);
    return new CommonDto<>(HttpStatus.OK.value());
  }


}
