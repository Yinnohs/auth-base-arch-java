package com.yinnohs.security.jwt.auth.infrastructure.configs;

import com.yinnohs.security.jwt.auth.domain.entities.SecurityConstants;
import com.yinnohs.security.jwt.auth.infrastructure.models.PermissionModel;
import com.yinnohs.security.jwt.auth.infrastructure.models.RoleModel;
import com.yinnohs.security.jwt.auth.infrastructure.repositories.PermissionsRepository;
import com.yinnohs.security.jwt.auth.infrastructure.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityDataInitializer {

    private final RoleRepository roleRepository;
    private final PermissionsRepository permissionRepository;

    @Bean
    public CommandLineRunner initSecurityData() {
        return args -> {
            log.info("Initializing security data...");
            initializePermissions();
            initializeRoles();
            assignPermissionsToRoles();
            log.info("Security data initialization completed");
        };
    }

    @Transactional
    public void initializePermissions() {
        List<String> permissionNames = Arrays.asList(
                SecurityConstants.USER_READ,
                SecurityConstants.USER_CREATE,
                SecurityConstants.USER_UPDATE,
                SecurityConstants.USER_DELETE
        );

        for (String permissionName : permissionNames) {
            if (!permissionRepository.existsByName(permissionName)) {
                PermissionModel permission = new PermissionModel();
                permission.setName(permissionName);
                permission.setDescription("Permission to " + permissionName.toLowerCase().replace('_', ' '));
                permissionRepository.save(permission);
                log.info("Created permission: {}", permissionName);
            }
        }
    }

    @Transactional
    public void initializeRoles() {
        List<String> roleNames = Arrays.asList(
                SecurityConstants.ROLE_ADMIN,
                SecurityConstants.ROLE_MANAGER,
                SecurityConstants.ROLE_USER,
                SecurityConstants.ROLE_INSTALLER
        );

        Map<String, String> roleDescriptions = new HashMap<>();
        roleDescriptions.put(SecurityConstants.ROLE_ADMIN, "Administrator with full access");
        roleDescriptions.put(SecurityConstants.ROLE_MANAGER, "Manager with elevated access");
        roleDescriptions.put(SecurityConstants.ROLE_USER, "Regular user with limited access");
        roleDescriptions.put(SecurityConstants.ROLE_INSTALLER, "Solar panel installer");

        for (String roleName : roleNames) {
            if (!roleRepository.existsByName(roleName)) {
                RoleModel role = new RoleModel();
                role.setName(roleName);
                role.setDescription(roleDescriptions.getOrDefault(roleName, "No description available"));
                roleRepository.save(role);
                log.info("Created role: {}", roleName);
            }
        }
    }

    @Transactional
    public void assignPermissionsToRoles() {
        // Define role-permission mappings
        Map<String, List<String>> rolePermissions = new HashMap<>();

        // Admin has all permissions
        List<String> allPermissions = Arrays.asList(
                SecurityConstants.USER_READ, SecurityConstants.USER_CREATE,
                SecurityConstants.USER_UPDATE, SecurityConstants.USER_DELETE
        );
        rolePermissions.put(SecurityConstants.ROLE_ADMIN, allPermissions);

        // Manager has most permissions except user deletion and some admin functions
        rolePermissions.put(SecurityConstants.ROLE_MANAGER, Arrays.asList(
                SecurityConstants.USER_READ, SecurityConstants.USER_CREATE, SecurityConstants.USER_UPDATE
        ));

        // Regular user has basic read permissions and can create sites
        rolePermissions.put(SecurityConstants.ROLE_USER, Arrays.asList(
                SecurityConstants.USER_READ, SecurityConstants.USER_CREATE,
                SecurityConstants.USER_UPDATE,
                SecurityConstants.USER_DELETE
        ));



        // Assign permissions to roles
        for (Map.Entry<String, List<String>> entry : rolePermissions.entrySet()) {
            String roleName = entry.getKey();
            List<String> permissionNames = entry.getValue();

            Optional<RoleModel> roleOptional = roleRepository.findByName(roleName);
            if (roleOptional.isPresent()) {
                RoleModel role = roleOptional.get();
                Set<PermissionModel> existingPermissions = role.getPermissions();

                // Get existing permission names
                Set<String> existingPermissionNames = new HashSet<>();
                for (PermissionModel p : existingPermissions) {
                    existingPermissionNames.add(p.getName());
                }

                // Add only missing permissions
                for (String permissionName : permissionNames) {
                    if (!existingPermissionNames.contains(permissionName)) {
                        Optional<PermissionModel> permissionOptional = permissionRepository.findByName(permissionName);
                        if (permissionOptional.isPresent()) {
                            role.addPermission(permissionOptional.get());
                            log.info("Added permission {} to role {}", permissionName, roleName);
                        }
                    }
                }

                roleRepository.save(role);
                log.info("Updated permissions for role: {}", roleName);
            }
        }
    }
}