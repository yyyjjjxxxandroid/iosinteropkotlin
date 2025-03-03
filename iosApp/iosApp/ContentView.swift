import SwiftUI
import shared
struct ContentView: View {
    let greet = Greeting().greet()
    let num = Greeting().kotlin()
    let messageee=Greeting().messageee()
    let helloswift=Greeting().getSwift()
	var body: some View {
        Text("pod MT库后只在真机上跑的起来")
        Spacer().frame(height: 20)
        Text(num)
        Spacer().frame(height: 20)
		Text(greet)
        Spacer().frame(height: 20)
        Text(messageee)
        Spacer().frame(height: 20)
        Text(helloswift)
    
       
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
