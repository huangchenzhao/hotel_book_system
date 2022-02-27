import http from '@http/http'
// 用户列表
export const userList = (obj) => http.GET('/api/book/list', obj)
