/**
 * 匹配邮箱地址
 * @param email
 * @returns {boolean}
 */
export const matchEmail = (email) => {
    let re =  /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/
    return re.test(email)
}

/**
 * 匹配数字
 * @param text
 * @returns {boolean}
 */
export const matchNumber = (text) => {
    let re =  /[1-9]\d*/
    return re.test(text)
}

