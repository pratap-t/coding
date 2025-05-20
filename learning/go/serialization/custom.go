package main

import (
	"encoding/json"
	"fmt"
)

type User struct {
	ID   int
	Name string
}

func (u User) JSONMarshal() ([]byte, error) {
	return json.Marshal(map[string]interface{}{
		"user_id":   u.ID,
		"user_name": u.Name,
	})
}

func main() {
	user := User{ID: 1, Name: "John Doe"}
	jsonData, _ := user.JSONMarshal()
	fmt.Println(string(jsonData))
}
