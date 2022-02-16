import SwiftUI
import shared

struct ContentView: View {

    
    @State var greet = "Loading..."
    @State var title = "Loading..."

    let greeting = Greeting()

    
    func load() {
        greeting.greeting {result, error in
            if let result = result {
                
                self.greet = result
            } else if let error = error {
                greet = "Error \(error)"
            }
        }
    }

    
	var body: some View {
        
        Text(greet).onAppear {
            load()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
