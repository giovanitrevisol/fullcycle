package main

import "net/http"

func main(){
	http.HandleFunc("/", Hello)
	http.ListenAndServe(":8000", nil)
}

func Hello(w http.ResponseWriter, r *http.Request){

	name := os.Getenv("NAME")
	age := os.Getenv("AGE")

	fmt.Fprint(w, "Hello, I am %s. I am %s.", name, age)

	w.Write([]byte("<h1>Hello Giovani!</h1>"))
}
f