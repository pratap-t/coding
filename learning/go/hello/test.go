package main

import "fmt"

func main() {
	var rows, k int
	rows = 8
	k = 0

	for i := 1; i <= rows; i++ {
		k = 0
		for j := 1; j <= rows-i; j++ {
			fmt.Print(" ")
		}
		for {
			fmt.Print("*")
			k++
			if k == 2*i-1 {
				break
			}
		}
		fmt.Println("")
	}
}
