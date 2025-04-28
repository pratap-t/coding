package main

import (
	"fmt"
	"time"
)

func pinger(c chan string) {
	for i := 0; ; i++ {
		c <- "ping"
	}
}

func printer(c chan string) {
	for i := 0; ; i++ {
		msg := <-c
		fmt.Println(msg)
		time.Sleep(time.Second * 1)
	}
}

func main() {
	var c chan string = make(chan string)
	go pinger(c)
	go printer(c)

	var input string
	fmt.Scanln(&input)
	fmt.Println("done")
	fmt.Println("input:", input)
	fmt.Println("c:", c)
	fmt.Println("pinger:", pinger)
	fmt.Println("printer:", printer)
	fmt.Println("main:", main)
	fmt.Println("time:", time.Now())
	fmt.Println("time:", time.Now().Unix())
	fmt.Println("time:", time.Now().UnixNano())
	fmt.Println("time:", time.Now().UnixMilli())
	fmt.Println("time:", time.Now().UnixMicro())
	fmt.Println("time:", time.Now().Format("2006-01-02 15:04:05"))
	fmt.Println("time:", time.Now().Format("2006-01-02 15:04:05.999999999"))
	fmt.Println("time:", time.Now().Format("2006-01-02 15:04:05.999999999999999999"))
	fmt.Println("time:", time.Now().Format("2006-01-02 15:04:05.999999999999999999000000000"))
	fmt.Println("time:", time.Now().Format("2006-01-02 15:04:05.999999999999999999000000000000000000"))
	fmt.Println("time:", time.Now().Format("2006-01-02 15:04:05.999999999999999999000000000000000000000000000"))
}
