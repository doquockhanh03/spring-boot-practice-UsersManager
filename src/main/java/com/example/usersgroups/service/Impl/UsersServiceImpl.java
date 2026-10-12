package com.example.usersgroups.service.Impl;

import com.example.usersgroups.common.Status;
import com.example.usersgroups.dto.request.CreateUserReq;
import com.example.usersgroups.dto.request.UpdateUserReq;
import com.example.usersgroups.entity.Users;
import com.example.usersgroups.repository.UsersRepo;
import com.example.usersgroups.service.UsersService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepo usersRepo;

    private final EntityManager entityManager;

    @Override
    public Users getUser(Long id) {
        Optional<Users> users = usersRepo.findById(id);
        if(users.isEmpty()){
            throw new RuntimeException("data not found!");
        }
        return users.get();
    }

    @Transactional
    @Override
    public Users createUser(CreateUserReq request) {
        if(request.getName().isEmpty() || request.getGender().isEmpty() ||
        request.getYearOfBirth() == null || request.getAddress().isEmpty()){
            throw new RuntimeException("Khong co du lieu truyen vao!");
        }

        Users users = new Users();

        users.setName(request.getName());
        users.setGender(request.getGender());
        users.setAddress(request.getAddress());
        users.setYearOfBirth(request.getYearOfBirth());
        users.setStatus(Status.ACTIVE);

        return usersRepo.save(users);
    }

    @Transactional
    @Override
    public Users updateUser(Long id, UpdateUserReq request) {
        Optional<Users> userExist = usersRepo.findById(id);
        if(userExist.isEmpty()){
            throw new RuntimeException("Khong thay id can sua!");
        }

        Users users = userExist.get();

        if(request.getName() != null){
            users.setName(request.getName());
        }

        if(request.getGender() != null){
            users.setGender(request.getGender());
        }

        if(request.getYearOfBirth() != null){
            users.setYearOfBirth(request.getYearOfBirth());
        }

        if(request.getAddress() != null){
            users.setAddress(request.getAddress());
        }

        return usersRepo.save(users);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        Optional<Users> userExist = usersRepo.findById(id);
        if(userExist.isEmpty()){
            throw new RuntimeException("Khong tim thay id can xoa");
        }
        Users users = userExist.get();

        users.setStatus(Status.INACTIVE);

        usersRepo.save(users);
    }

    @Override
    public List<Users> filter(String name, String gender, Long minYearOfBirth, Long maxYearOfBirth) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Users> query = criteriaBuilder.createQuery(Users.class);

        Root<Users> root = query.from(Users.class);

        List<Predicate> predicates = new ArrayList<>();

        if(name != null && !name.isEmpty()){
            Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
            predicates.add(predicate);
        }

        if(gender != null && !gender.isEmpty()){
            Predicate predicate = criteriaBuilder.like(root.get("gender"), "%" + gender + "%");
            predicates.add(predicate);
        }

        if(minYearOfBirth != null){
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("yearOfBirth"), minYearOfBirth);
            predicates.add(predicate);
        }

        if(maxYearOfBirth != null){
            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("yearOfBirth"), maxYearOfBirth);
            predicates.add(predicate);
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
