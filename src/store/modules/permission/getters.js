export default {
  getPermission: state => userType => state.permissionMap[userType],
  getWhiteList: state => state.whiteList
}
