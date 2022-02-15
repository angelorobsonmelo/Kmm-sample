import SwiftUI
import shared

struct ContentView: View {

    
    @State var greet = "Loading..."
    @State var title = "Loading..."

    let service = PostRemoteRepositoryCompanion.shared.create()
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

    func loadFromApi() {
        service.getPosts{result, error in
            if let result = result {
                self.title = result.randomElement()?.title ?? ""
            } else if let error = error {
                title = "Error \(error)"
            }
        }
    }
    
	var body: some View {
        
        Text(greet).onAppear {
            load()
        }
        
        Text(title).onAppear {
            loadFromApi()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
