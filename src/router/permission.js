function findRoute (to, moduleObj, routeObj) {
  let children = moduleObj.children
  for (let i = 0; i < children.length; i++) {
    let obj = children[i]
    if (obj.name === to.name) {
      routeObj.isFound = true
      if (obj.hasOwnProperty('children')) {
        routeObj.name = obj.children[0].name
      } else {
        routeObj.name = true
      }
      break
    } else {
      if (obj.hasOwnProperty('children')) {
        findRoute(to, obj, routeObj)
      }
    }
  }
}
export default (store, routes, to, next) => {
  let userType = store.getters['user/getUserType']
  if (!userType) {
    next(new Error('userType字段不能为空'))
    return false
  }
  let protectedRoutes = store.getters['permission/getPermission'](userType)

  let moduleName = to.matched[0].name
  if (!moduleName) {
    next(new Error('路由请添加name字段'))
    return false
  }
  if (!protectedRoutes || Array.isArray(protectedRoutes)) {
    next(new Error('路由保护验证错误：protectedRoutes字段必须以对象格式传入，eg: protectedRoutes: {\n' +
      '        main: [\'first\', \'second\']\n' +
      '      }'))
    return false
  }
  if (!protectedRoutes.hasOwnProperty(moduleName)) {
    next(new Error('路由保护验证错误：protectedRoutes对象中没有找到对应的key'))
    return false
  }
  if (!Array.isArray(protectedRoutes[moduleName])) {
    next(new Error('路由保护验证错误：保护路由的传入格式错误，eg:protectedRoutes: {\n' +
      '        main: [\'first\', \'second\']\n' +
      '      }'))
    return false
  }
  if (protectedRoutes[moduleName][0] === '*') {
    let moduleObj = routes.find(item => item.name === moduleName)
    if (!moduleObj.hasOwnProperty('children')) {
      next()
      return false
    }
    if (moduleObj.name === to.name) {
      next({name: moduleObj.children[0].name})
      return false
    }
    let routeObj = {
      isFound: false,
      name: 'forbidden'
    }
    findRoute(to, moduleObj, routeObj)
    if (routeObj.isFound) {
      if (typeof routeObj.name === 'boolean') {
        next()
      } else {
        next({name: routeObj.name})
      }
    } else {
      next({name: 'forbidden'})
    }
    return false
  }
  if (protectedRoutes[moduleName].indexOf(to.name) > -1) {
    next()
    // 如果往模块root节点跳转就往第一个受保护的路由跳转
  } else if (moduleName === to.name) {
    next({name: protectedRoutes[moduleName][0]})
  } else {
    next({name: 'forbidden'})
  }
}
