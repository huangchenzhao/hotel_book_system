import http from '@http/http'
// 用户列表
export const userList = (obj) => http.GET('/api/book/list', obj)
// 用户登陆
export const user = (obj) => http.GET('/api/user/login', obj)
// 用户注册
export const regUser = (obj) => http.GET('/api/user/register', obj)
// 搜索酒店
export const searchHotel = (obj) => http.GET('/api/hotel/search', obj)
// 发送邮箱验证码
export const sendEmail = (obj) => http.GET('/api/user/sendEmail', obj)
// 验证码是否正确
// export const verifyCode = (obj) => http.GET('/api/user/verify', obj)
// 获取酒店详情
export const getDetail = (obj) => http.GET('/api/hotel/detail', obj)
// 每个页面都获取用户信息
export const getUserInfo = () => http.GET('/api/user/detail')
// 获取上传到后端的头像，上传到数据库
export const getTempPhoto = () => http.GET('/api/file/yesurl')
// 用户下单
export const reserve = (obj) => http.GET('/api/userorder/placeOrder', obj)
// 下单确定写到数据库中
export const confirmOrder = (obj) => http.GET('/api/userorder/placeOrder', obj)
// 用户评价
export const writeComment = (obj) => http.GET('/api/userorder/placeOrder', obj)
