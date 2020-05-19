/** https://leetcode.com/problems/hamming-distance/
 * @param {number} x
 * @param {number} y
 * @return {number}
 */
var hammingDistance = function(x, y) {
    let arrBinaryX = intToBinary(x);
    let arrBinaryY = intToBinary(y);
    
    let lengthX = arrBinaryX.length;
    let lengthY = arrBinaryY.length;
    if (lengthX !== lengthY) {
        if (lengthX < lengthY) {
            arrBinaryX = fillArray(arrBinaryX, lengthY);
        } else {
            arrBinaryY = fillArray(arrBinaryY, lengthX);
        }
    }
    
    return countDiff(arrBinaryX, arrBinaryY);
};

var intToBinary = (x) => {
    let arrBinary = [];
    let i = 0;
    while(x !== 0) {
        arrBinary[i] = (x % 2);
        x = Math.floor(x / 2);
        i++;
    }
    arrBinary[i] = 0;
    
    return arrBinary;
}

var fillArray = (arr, i) => {
    let rLength = arr.length;
    arr.length = i;
    for (let index = rLength; index < i; index++) {
        arr[index] = 0;
    }
    return arr;
}

var countDiff = (arrX, arrY) => {
    let length = arrX.length;
    let count = 0;
    for (let i = 0; i < length; i++) {
        if (arrX[i] !== arrY[i]) {
            count ++;
        }
    }
    
    return count;
}