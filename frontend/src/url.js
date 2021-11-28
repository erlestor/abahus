// url is the url of our rest api
// this hopes to solve the issue with gitpod url's being different very time
// if this doesn't work please just set url = your gitpod url and don't give us a F

let url = window.location.href

if (window.location.pathname === "/") {
  url = url.substring(0, url.length - 1)
} else {
  url = url.replace(window.location.pathname, "")
}

url = url.replace("3000", "8080")

export default url
