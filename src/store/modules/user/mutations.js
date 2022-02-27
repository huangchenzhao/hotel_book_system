import types from './types'
export default {
  [types.SET_USER_ID] (state, id) {
    state.id = id
  },
  [types.SET_USER_NAME] (state, name) {
    state.name = name
  },
  [types.SET_USER_TYPE] (state, type) {
    state.type = type
  }
}
