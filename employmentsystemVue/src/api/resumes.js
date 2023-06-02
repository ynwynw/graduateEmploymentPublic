import request from '../utils/request';

export const selectResumes = (phone) => {
  return request({
    url: '/api/selectResumes',
    method: 'post',
    params: {
      phone
    }
  });
};

export const findDatasList = (current,size,dataVO) => {
  return request({
    url: '/api/findResumesPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: dataVO
  });
};
export const findResumesDatasList2 = (current,size) => {
  return request({
    url: '/api/findResumesPage2',
    method: 'post',
    params: {
      current,
      size
    }
  });
};
export const findCompanyresumesDatasList = (current,size,dataVO) => {
  return request({
    url: '/api/findResumesPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: dataVO
  });
};
export const findDatasForUserList = (current,size,dataVO,phone) => {
  return request({
    url: '/api/findDatasForUserList',
    method: 'post',
    params: {
      current,
      size,
      phone
    },
    data: dataVO
  });
};
export const findUserApplysDatasForUserList = (current,size,dataVO,phone) => {
  return request({
    url: '/api/findUserApplysDatasForUserList',
    method: 'post',
    params: {
      current,
      size,
      phone
    },
    data: dataVO
  });
};
export const findCompanyApplysDatasForUserList = (current,size,dataVO,phone) => {
  return request({
    url: '/api/findCompanyApplysDatasForUserList',
    method: 'post',
    params: {
      current,
      size,
      phone
    },
    data: dataVO
  });
};
export const findAdminApplysDatasForUserList = (current,size,dataVO,phone) => {
  return request({
    url: '/api/findAdminApplysDatasForUserList',
    method: 'post',
    params: {
      current,
      size,
      phone
    },
    data: dataVO
  });
};
export const findInstructorsApplysDatasForUserList = (current,size,dataVO,phone) => {
  return request({
    url: '/api/findInstructorsApplysDatasForUserList',
    method: 'post',
    params: {
      current,
      size,
      phone
    },
    data: dataVO
  });
};
export const findDaishenheCompanyApplysDatasForUserList = (current,size,phone) => {
  return request({
    url: '/api/findDaishenheCompanyApplysDatasForUserList',
    method: 'post',
    params: {
      current,
      size,
      phone
    }
  });
};
export const findSuccessCompanyApplysDatasForUserList = (current,size,phone) => {
  return request({
    url: '/api/findSuccessCompanyApplysDatasForUserList',
    method: 'post',
    params: {
      current,
      size,
      phone
    }
  });
};
export const saveResumes= (data,phone) => {
  return request({
    url: '/api/saveResumes',
    method: 'post',
    params: {
      phone
    },
    data: data
  });
};

export const updateResumes= (data) => {
  return request({
    url: '/api/updateResumes',
    method: 'post',
    data: data
  });
};

export const deleteDatas= (id) => {
  return request({
    url: '/api/deleteResumes',
    method: 'post',
    params: {
      id
    }
  });
};

export const deleteUserApplysDatas= (id) => {
  return request({
    url: '/api/deleteUserApplysDatas',
    method: 'post',
    params: {
      id
    }
  });
};

export const deleteCompanyApplysDatas= (id) => {
  return request({
    url: '/api/deleteCompanyApplysDatas',
    method: 'post',
    params: {
      id
    }
  });
};
export const deleteAdminApplysDatas= (id) => {
  return request({
    url: '/api/deleteCompanyApplysDatas',
    method: 'post',
    params: {
      id
    }
  });
};
export const passApplys= (id) => {
  return request({
    url: '/api/passApplys',
    method: 'post',
    params: {
      id
    }
  });
};
export const signagreementDatas= (id) => {
  return request({
    url: '/api/signagreementDatas',
    method: 'post',
    params: {
      id
    }
  });
};
export const nopassApplys= (id) => {
  return request({
    url: '/api/nopassApplys',
    method: 'post',
    params: {
      id
    }
  });
};
export const agreesigntriagreementDatas= (id) => {
  return request({
    url: '/api/agreesigntriagreementDatas',
    method: 'post',
    params: {
      id
    }
  });
};
export const noagreesigntriagreementDatas= (id) => {
  return request({
    url: '/api/noagreesigntriagreementDatas',
    method: 'post',
    params: {
      id
    }
  });
};
export const auditsignDatas= (id) => {
  return request({
    url: '/api/auditsignDatas',
    method: 'post',
    params: {
      id
    }
  });
};
export const noauditsignDatas= (id) => {
  return request({
    url: '/api/noauditsignDatas',
    method: 'post',
    params: {
      id
    }
  });
};
export const adminauditsignDatas= (id) => {
  return request({
    url: '/api/adminauditsignDatas',
    method: 'post',
    params: {
      id
    }
  });
};
export const adminnoauditsignDatas= (id) => {
  return request({
    url: '/api/adminnoauditsignDatas',
    method: 'post',
    params: {
      id
    }
  });
};

