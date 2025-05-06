package hello

import "testing"

func TestHello(t *testing.T) {
	// Test the hello function
	want := "Ahoy, world!"
	// actual := hello()
	if got := Hello(); got != want {
		t.Errorf("Hello() = %q, want %q", got, want)
	}

	// Test the proverb function
	want = "Concurrency is not parallelism."
	if got := proverb(); got != want {
		t.Errorf("proverb() = %q, want %q", got, want)
	}
}
