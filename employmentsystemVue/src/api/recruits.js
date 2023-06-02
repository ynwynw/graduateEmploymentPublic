import request from '../utils/request';

export const findDatasList = (current,size,dataVO) => {
  return request({
    url: '/api/findRecruitsPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: dataVO
  });
};
export const findResurtsDatasList2 = (current,size) => {
  return request({
    url: '/api/findRecruitsPage2',
    method: 'post',
    params: {
      current,
      size
    }
  });
};
export const findDatasForCompanyList = (current,size,dataVO,phone) => {
  return request({
    url: '/api/findDatasForCompanyList',
    method: 'post',
    params: {
      current,
      size,
      phone
    },
    data: dataVO
  });
};
export const findTongjiList = (current,size,userVO) => {
  return request({
    url: '/api/findRecruitsPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  });
};
export const saveRecruits= (data,phone) => {
  return request({
    url: '/api/saveRecruits',
    method: 'post',
    params: {
      phone
    },
    data: data
  });
};

export const updateRecruits= (data) => {
  return request({
    url: '/api/updateRecruits',
    method: 'post',
    data: data
  });
};

export const applyDatas= (data,resumetitle,phone) => {
  return request({
    url: '/api/applyRecruits',
    method: 'post',
    params: {
      resumetitle,
      phone
    },
    data: data
  });
};

export const findCompanysById= (id) => {
  return request({
    url: '/api/findUserById',
    method: 'post',
    params: {
      id
    }
  });
};

export const deleteDatas= (id) => {
  return request({
    url: '/api/deleteRecruits',
    method: 'post',
    params: {
      id
    }
  });
};
