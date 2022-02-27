export function _isType (t) {
  return Object.prototype.toString.call(t).slice(8, -1).toLowerCase()
}

export function _saveLocalStorage (key, value) {
  if (_isType(value) === 'string') {
    localStorage.setItem(key, value)
  } else {
    let Info = JSON.stringify(value)
    localStorage.setItem(key, Info)
  }
}

export function _getLocalStorage (item) {
  return localStorage.getItem(item)
}

export function _clearStorage () {
  localStorage.clear()
}

export function _getCookie (cName) {
  let cStart, cEnd
  if (document.cookie.length > 0) {
    cStart = document.cookie.indexOf(cName + '=')
    if (cStart !== -1) {
      cStart = cStart + cName.length + 1
      cEnd = document.cookie.indexOf(';', cStart)
      if (cEnd === -1) cEnd = document.cookie.length
      return document.cookie.slice(cStart, cEnd)
    }
  }
  return ''
}

export function _keyMirror (obj) {
  if (obj instanceof Object) {
    var _obj = Object.assign({}, obj)
    var _keyArray = Object.keys(obj)
    _keyArray.forEach((key) => {
      _obj[key] = key
      return true
    })
    return _obj
  }
}

export function _isArrayFn (value) {
  if (typeof Array.isArray === 'function') {
    return Array.isArray(value)
  } else {
    return Object.prototype.toString.call(value) === '[object Array]'
  }
}

export function _pagination (pageNo, pageSize, array) {
  let offset = (pageNo - 1) * pageSize
  return (offset + pageSize >= array.length) ? array.slice(offset, array.length) : array.slice(offset, offset + pageSize)
}
