export default {
  sidebarMap: {
    1: {
      main: [
        {
          groupTitle: '分组1'
        },
        {
          path: '/main/first',
          icon: 'suncaper-menu-unfold',
          title: '一级菜单1'
        },
        {
          path: '/main/second',
          icon: 'suncaper-menu-unfold',
          title: '一级菜单2',
          group: 'second',
          children: [
            {
              path: '/main/second/second',
              icon: 'suncaper-menu-unfold',
              title: '二级菜单'
            }
          ]
        },
        {
          path: '/main/user',
          icon: 'suncaper-menu-unfold',
          title: '用户管理',
          group: 'user',
          children: [
            {
              path: '/main/user/list',
              icon: 'suncaper-menu-unfold',
              title: '用户列表'
            }
          ]
        }
      ]
    }
  },
  sidebarTheme: {
    dark: {
      background: '#1f2c35',
      text: '#ffffff',
      activeText: '#ffffff'
    },
    light: {
      background: '#ffffff',
      text: '#000000',
      activeText: '#1890ff'
    }
  }
}
