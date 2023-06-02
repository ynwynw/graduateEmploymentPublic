import request from '../utils/request';

export const uploadimg= (imgUrl,imgName,imgtype) => {
  return request({
    url: '/api/uploadImg',
    method: 'post',
    params:{
      imgtype,
      imgName
    },
    data: imgUrl
  });
};

export const uploadFiles= (filesUrl, filesDbName,phone,filesOldName,filetype,type ) => {
  return request({
    url: '/api/uploadFiles',
    method: 'post',
    params:{
      filesDbName,
      phone,
      filesOldName,
      filetype,
      type
    },
    data: filesUrl
  });
};
