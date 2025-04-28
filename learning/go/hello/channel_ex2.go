package main

import (
	"fmt"
	"time"
)

func pinger(c chan string, quit chan bool) {
	for {
		select {
		case c <- "ping":
		case <-quit: // exit the loop when quit signal is received
			fmt.Println("Pinger exiting...")
			return
		}
	}
}

func printer(c chan string, quit chan bool) {
	for {
		select {
		case msg := <-c:
			fmt.Println(msg)
			fmt.Println(time.Second * 1)
		case <-quit: // Exit the loop when quit signal is received
			fmt.Println("Printer exiting...")
			return
		}
	}
}

func main() {
	var c chan string = make(chan string)
	var quit chan bool = make(chan bool)
	go pinger(c, quit)
	go printer(c, quit)

	// Wait for user input to terminate the program
	fmt.Println("Press Enter to stop...")
	var input string
	fmt.Scanln(&input)

	// Send quit signal to both goroutines
	quit <- true
	quit <- true

	// Wait briefly to ensure goroutines exit
	time.Sleep(time.Second * 1)
	fmt.Println("Program terminated.")
}
