package com.example.tbcclasswork5.room

class UserRepository(private val userDao: UserDao,private val permissionDao:PermissionDao,private val userPermissionDao:UserPermissionDao) {
    fun getAllUsersFlow() = userDao.readAllData()

    suspend fun insert(user:User){
        userDao.addUser(user)
    }
   /* suspend fun update(user:User){
        userDao.updateUser(user)
    }
    suspend fun delete(user:User){
        userDao.deleteUser(user)
    }*/

 /*   suspend fun insertUserWithPermissions(user: User, permissions: List<String>) {
        userDao.addUser(user)

        permissions.forEach { permission ->
            if (permissionDao.getPermissionByName(permission) == null) {
                permissionDao.addPermission(Permission(permission))
            }
            userPermissionDao.addUserPermission(UserPermission(user.id, permission))
        }
    }

    suspend fun updateUserPermissions(userId: Int, permissions: List<String>) {
        userPermissionDao.deleteUserPermissionsByUserId(userId)
        permissions.forEach { permission ->
            if (permissionDao.getPermissionByName(permission) == null) {
                permissionDao.addPermission(Permission(permission))
            }
            userPermissionDao.addUserPermission(UserPermission(userId, permission))
        }
    }

    fun getPermissionsForUser(userId: Int) = userPermissionDao.getPermissionsForUser(userId)*/

}