export default {
  whiteList: ['/', 'notFound', 'login', 'forbidden', 'badGateway'],
  permissionMap: {
    0: {
      list: ['list'],
      hotelList: ['hotelList'],
      addHotel: ['addHotel'],
      userList: ['userList'],
      orderList: ['orderList'],
      orderCharts: ['orderCharts']
    },
    1: {
      userPage: ['userPage'],
      searchResult: ['searchResult'],
      userOrder: ['userOrder']
    }
  }
}
