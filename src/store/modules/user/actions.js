import { _getUserInfo } from '@api/user'
import types from './types'
export default {
  async fetchUserInfo ({ commit }) {
    let res = await _getUserInfo()
    if (res.status) {
      commit(types.SET_USER_ID, res.data.id)
      commit(types.SET_USER_NAME, res.data.name)
      commit(types.SET_USER_TYPE, res.data.userType)
    }
    return res
  }
}
