import http from '@http/http'
// 用户列表
export const userList = (obj) => http.GET('/api/book/list', obj)
// 用户登陆
export const user = (obj) => http.GET('/api/user/login', obj)
// 用户注册
export const regUser = (obj) => http.GET('/api/user/register', obj)
// 搜索酒店
export const searchHotel = (obj) => http.GET('/api/hotel/search', obj)
