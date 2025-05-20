package main

import (
	"bytes"
	"encoding/gob"
	"fmt"
)

type User struct {
	ID   int
	Name string
}

func main() {
	// Serialize with gob
	user := User{ID: 1, Name: "Alice"}
	var buf bytes.Buffer
	encoder := gob.NewEncoder(&buf)
	encoder.Encode(user)
	fmt.Println("Serialized data:", buf.Bytes())

	// deserialize with gob
	var decodedUser User
	decoder := gob.NewDecoder(&buf)
	decoder.Decode(&decodedUser)
	fmt.Println("Decoded user:", decodedUser)
}
