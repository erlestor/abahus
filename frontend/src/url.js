let url = window.location.href

if (window.location.pathname === "/") {
  url = url.substring(0, url.length - 1)
} else {
  url = url.replace(window.location.pathname, "")
}

url = url.replace("3000", "8080")

export default url
