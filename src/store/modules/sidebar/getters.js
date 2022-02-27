export default {
  getSidebarList: state => userType => state.sidebarMap[userType],
  getSidebarTheme: state => theme => state.sidebarTheme[theme]
}
