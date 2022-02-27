import http from '@http/http'
// 用户列表
export const userList = (obj) => http.GET('/api/book/list', obj)
// 用户登陆
export const userLogin = (obj) => http.GET('/api/admin/login', obj)
