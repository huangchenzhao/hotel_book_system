import Vue from 'vue'

export const clearHttpRequestingList = () => {
  if (Vue.$httpRequestList.length > 0) {
    Vue.$httpRequestList.forEach((item) => {
      item()
    })
    Vue.$httpRequestList = []
  }
}
