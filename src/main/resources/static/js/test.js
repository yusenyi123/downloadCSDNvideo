var iconv = require('iconv-lite'); //引入模块
var Crypto = require('crypto-js');
var request = require('request');

var nonceFunc = function() {
    return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, (function(e) {
            var n = 16 * Math.random() | 0
                , t = "x" === e ? n : 3 & n | 8;
            return t.toString(16)
        }
    ));
}

function loc() {
    var   e = +new Date / 1e3 | 0;
    return e.toString(36)
}

var nonce = nonceFunc(), appSecret = "Et9j9OntJ0zNRQvFxZoL4N9Y2uPDsoe4", h="";
//nonce="27d16965-038c-4151-b4d9-913295226834"

h += "".concat("GET", "\n");
h += "".concat("application/json, text/plain, */*", "\n");
h += "".concat("", "\n");
h += "".concat("", "\n");
h += "".concat("", "\n");

h += "x-ca-key:203866374\n";//目前看来也是固定值
h += "x-ca-nonce:" + nonce + "\n";
h += "/edu-academy-web/v1/material/info?cId=4545&courseId=4545&isFree=2&isMember=2&materialId=214363&playerVersion=2"


var hash = Crypto.HmacSHA256(h, appSecret);
var hashInBase64 = Crypto.enc.Base64.stringify(hash);
console.log(nonce);
console.log(hashInBase64);

mycookie='uuid_tt_dd=10_6140429900-1639969770257-216531; c_first_ref=www.baidu.com; c_segment=3; dc_sid=85e0e3407d43ceca9538ba13d9269e88; SESSION=6494c9d7-67ea-4203-9864-04ca60e5fba4; UserName=qq_42532156; UserInfo=9cad350a2666468ea469b4454e234e8b; UserToken=9cad350a2666468ea469b4454e234e8b; UserNick=qq_42532156; AU=4B0; UN=qq_42532156; BT=1639969752850; p_uid=U010000; ssxmod_itna=Yq+hGIx0xfxUhEDz1Yb40K0QGCB7t5PD7CKvf7UDBwiPiNDnD8x7YDvmINYpKK50Bmwa4cChuXFvKc+G4QLWAr23Q=qtG+eDHxY=DUErkKoD4SKGwD0eG+DD4DWUx03DoxGYMIx0RMSg6ykExiOD7eDXxGCDQKhXxGWDiPD7ZIKpQRkLxi7DD5DnhAvq4DWDWckBgp+eRYA8GvxXxG1DQ5Ds2DU8SOUMt+sBPLxuQGqDBbxYwDR7Qi4DmR3EhY+DCKDjZA1G2YRRxAzIP6N5j4nbQ+NT0id8le55Q+K7CGe1GG4tGxoFj0xnGq/hZUvPF54dPxKhP00PeD==; ssxmod_itna2=Yq+hGIx0xfxUhEDz1Yb40K0QGCB7t5PD7CKvf7D8T1xqGXqIqGaKnf1UNzx8h7e6zQAr0hNq45We9Npn7QRvKhcQ+WYcLxM6CLi4QsP9oxt2fFm52R52idBaL+HfZBb5PXHQlc/2k8otekY8KlfzWGIyiWrLtfvylbxsWndKfetZi+fR8+D9jqoRSqbPZnvl+qRyF=a0YeY=rWc=MOUQlOoHM8FYZ8uB3+qE4dUxzr7iTUYqcGg0/nikc4i1d+Hlj=QL4mI5ht78epL/0GDIMPuR16CuT5x2VE1hRdHUlSAR5tCqAQQMZQ+Df==AxmX2imx0Y7QG8AxP1P5ttXn0lGw==r=WfHQGGjrSW5ebDfB2=ONLrmYze+Q0ZApK9jXn53EP2lIx4wx6RHQYMxq+Y3wOwwQnueWcQIG7XVP6+GoPIrs=IEtW=ljGeeQAN7xeUFKmhXmRP74Ngru06affpcOw+ADaDG200FdFiB0xKmK32+fM74qzcdsgDRmczswKQoA0iT5yYijoairqRqKeK3D=YxIbben3IXQDFqD+=4xD; log_Id_click=2; c_utm_source=studyvip_pcxd; c_first_page=https%3A//blog.csdn.net/u014569188/article/details/78912446; log_Id_view=6; dc_session_id=10_1640048546660.455037; c_pref=https%3A//www.baidu.com/link; c_ref=https%3A//edu.csdn.net/help; c_page_id=default;'
mycookie=mycookie+'dc_tos='+loc()+'; log_Id_pv=14'

var options = {
    'method': 'GET',
     'encoding': null, //设置该属性为null,返回的数据以字节形式接受,可以后续自行进行解码
     'gzip': true,  //返回的数据是gzip压缩过的,设置该属性为true,对返回的数据进行解压缩恢复原先数据
    'url': 'https://bizapi.csdn.net/edu-academy-web/v1/material/info?materialId=214363&courseId=4545&cId=4545&playerVersion=2&isFree=2&isMember=2',
    'headers': {
        'accept': 'application/json, text/plain, */*',
        'accept-encoding': 'gzip, deflate, br',
        'accept-language': 'zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6',
        'cookie': mycookie,
        'origin': 'https://edu.csdn.net',
        'referer': 'https://edu.csdn.net/learn/4545',
        'sec-ch-ua': '" Not A;Brand";v="99", "Chromium";v="96", "Microsoft Edge";v="96"',
        'sec-ch-ua-mobile': '?0',
        'sec-ch-ua-platform': '"Windows"',
        'sec-fetch-dest': 'empty',
        'sec-fetch-mode': 'cors',
        'sec-fetch-site': 'same-site',
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36 Edg/96.0.1054.62',
        'x-ca-key': '203866374',
        'x-ca-nonce': nonce,
        'x-ca-signature': hashInBase64,
        'x-ca-signature-headers': 'x-ca-key,x-ca-nonce'
    }
};
console.log(options)
request(options, function (error, response) {
    console.log("start")
    console.log(response.headers)
    console.log(response.body)
    console.log(response.statusCode)
    var buf = iconv.decode(response.body, 'utf-8').toString(); //解码gb2312
    console.log(buf);
});