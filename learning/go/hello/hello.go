package main

import (
    "fmt"
    "log"
    "example.com/greetings"
)

func main() {
    // Get a greeting message and print it.
    message, error := greetings.Hello("Gladys")
	if error != nil {
		log.Fatal(error)
	}
    fmt.Println(message)
}