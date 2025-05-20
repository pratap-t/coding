package main

import (
	"encoding/xml"
	"fmt"
)

type Product struct {
	XMLName xml.Name `xml:"product"`
	Name    string   `xml:"name"`
	Price   float64  `xml:"price"`
}

func main() {
	// struct to XML serialization
	product := Product{Name: "Alice", Price: 100.50}
	xmlData, _ := xml.MarshalIndent(product, "", "  ")
	fmt.Println(string(xmlData))

	// XML to struct deserialization
	xmlString := `<product><name>Bob</name><price>200.75</price></product>`
	var newProduct Product
	xml.Unmarshal([]byte(xmlString), &newProduct)
	fmt.Println(newProduct)
}
