export default {
  getRoleName: state => userType => state.roleTypeMap[userType].roleName
}
