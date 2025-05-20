package main

import (
	"encoding/json"
	"fmt"
)

type User struct {
	ID    int    `json:"id"`
	Name  string `json:"name"`
	Email string `json:"email"`
}

func main() {
	// Struct to JSON serialization
	user := User{ID: 1, Name: "Alice", Email: "alice@example.com"}
	jsonData, _ := json.Marshal(user)
	fmt.Println(string(jsonData))

	// JSON to Struct deserialization
	jsonString := `{"id":2,"name":"Bob","email":"bob@example.com"}`
	var newUser User
	json.Unmarshal([]byte(jsonString), &newUser)
	fmt.Println(newUser)

}
