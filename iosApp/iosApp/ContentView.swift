import SwiftUI
import shared
struct ContentView: View {
    let greet = Greeting().greet()
    let num = Greeting().kotlin()
	var body: some View {
		Text(greet)
        Text(num)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
