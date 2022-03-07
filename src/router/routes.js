const loginPage = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'login',
    component: () =>
      import(/* webpackChunkName: "Login" */ '../pages/login/Login')
  },
  {
    path: '/userPage',
    name: 'userPage',
    component: () =>
      import(/* webpackChunkName: "Login" */ '../pages/main/routes/user/userPage')
  },
  {
    path: '/searchResult',
    name: 'searchResult',
    component: () =>
      import(/* webpackChunkName: "Login" */ '../pages/main/routes/user/searchResult')
  },
  {
    path: '/userOrder',
    name: 'userOrder',
    component: () =>
      import(/* webpackChunkName: "Login" */ '../pages/main/routes/user/userOrder')
  },
  {
    path: '/list',
    name: 'list',
    component: () =>
      import(/* webpackChunkName: "Login" */ '../pages/main/routes/admin/list')
  },
  {
    path: '/hotel/list',
    name: 'hotelList',
    component: () =>
      import(/* webpackChunkName: "Login" */ '../pages/main/routes/admin/hotelList')
  },
  {
    path: '/hotel/add',
    name: 'addHotel',
    component: () =>
      import(/* webpackChunkName: "Login" */ '../pages/main/routes/admin/addHotel')
  },
  {
    path: '/hotel/manage',
    name: 'manageHotel',
    component: () =>
      import(/* webpackChunkName: "Login" */ '../pages/main/routes/admin/manageHotel')
  },
  {
    path: '/user/list',
    name: 'userList',
    component: () =>
      import(/* webpackChunkName: "Login" */ '../pages/main/routes/admin/userList')
  }
]

const mainPage = [
  {
    path: '/main',
    name: 'main',
    component: () =>
      import(/* webpackChunkName: "Main" */ '../pages/main/Main'),
    children: [
      {
        path: 'first',
        name: 'first',
        component: () =>
            import(
              /* webpackChunkName: "Second" */ '../pages/main/routes/first/First'
            )
      },
      {
        path: 'second/second',
        name: 'second',
        component: () =>
            import(
              /* webpackChunkName: "First" */ '../pages/main/routes/second/Second'
            )
      },
      {
        path: 'user/list',
        name: 'userList',
        component: () =>
          import(
            /* webpackChunkName: "First" */ '../pages/main/routes/user/userList'
          )
      }
    ]
  }
]

const errorPage = [
  {
    path: '/notFound',
    name: 'notFound',
    component: () =>
      import(/* webpackChunkName: "NotFound" */ '../pages/error/NotFound')
  },
  {
    path: '/forbidden',
    name: 'forbidden',
    component: () =>
      import(/* webpackChunkName: "Forbidden" */ '../pages/error/Forbidden')
  },
  {
    path: '/badGateway',
    name: 'badGateway',
    component: () =>
      import(/* webpackChunkName: "BadGateway" */ '../pages/error/BadGateway')
  },
  {
    path: '*',
    redirect: '/notFound'
  }
]
export default [...loginPage, ...mainPage, ...errorPage]
