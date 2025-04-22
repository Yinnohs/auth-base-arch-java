package com.yinnohs.security.jwt.auth.infrastructure.services;

import com.yinnohs.security.jwt.auth.domain.entities.Role;
import com.yinnohs.security.jwt.auth.domain.exceptions.RoleNotFoundException;
import com.yinnohs.security.jwt.auth.domain.ports.out.RoleService;
import com.yinnohs.security.jwt.auth.infrastructure.mappers.RoleMapper;
import com.yinnohs.security.jwt.auth.infrastructure.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public Role finRoleByName(String name) {
        var errorMessage = String.format("[ERROR]: role does not exists role name : %s", name);
        var roleModel = repository.findByName(name).orElseThrow(()-> new RoleNotFoundException(errorMessage));
        return  mapper.modelToDomain(roleModel);
    }

    @Override
    public Role save(Role role) {
        var roleModel = mapper.domainToModel(role);
        var savedRoleModel = repository.save(roleModel);
        return mapper.modelToDomain(savedRoleModel);
    }
}
