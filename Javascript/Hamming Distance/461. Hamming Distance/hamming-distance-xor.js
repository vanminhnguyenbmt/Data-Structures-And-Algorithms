/** https://leetcode.com/problems/hamming-distance/
 * @param {number} x
 * @param {number} y
 * @return {number}
 */
var hammingDistance = function(x, y) {
    let xor = x ^ y;
    return countDistance(xor);
};

var countDistance = (x) => {
    let result = 0;
    while(x !== 0) {
        if (x % 2 === 1) {
            result++;
        }
        x = x >> 1;
    }
    
    return result;
}