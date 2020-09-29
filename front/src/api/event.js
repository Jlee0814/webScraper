import request from '@/utils/request'

export default{
  list() {
    return request({
      url:'/event/list',
      method: 'get'
    })
  },

  pageList(page, limit, searchObj) {
    return request({
      url:`/event/list/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  }
}
