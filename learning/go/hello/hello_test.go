package hello

import "testing"

func TestHello(t *testing.T) {
	// Test the hello function
	want := "Hello, world."
	// actual := hello()
	if got := Hello(); got != want {
		t.Errorf("Hello() = %q, want %q", got, want)
	}
}
