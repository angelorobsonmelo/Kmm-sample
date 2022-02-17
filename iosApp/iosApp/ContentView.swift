import SwiftUI
import shared

struct ContentView: View {
    
    
    @State var greet = "Loading..."
    
    
    private var viewmodel = PostsViewModel()
    
    
    func load() {
        viewmodel.getPosts()
        
        viewmodel.allPostsLiveData.addObserver { (state) in
            switch state as! NetworkResult {
            case is NetworkResultIdle<AnyObject>:
                print("Handle Idle state here")
                
            case is NetworkResultLoading<AnyObject>:
                print("Handle loading state here")
                
            case is NetworkResultSuccess<AnyObject>:
                print("Handle success success here")
                let response = (state as! NetworkResultSuccess)
                let postsResponse = response.data as! [PostResponse]
                
                
                self.greet = postsResponse.randomElement()!.title
                
            case is NetworkResultError<AnyObject>:
                print("handle error state here!")
                let response = (state as! NetworkResultError)
                let messageError = response.message as! [String]
                
                self.greet = "Error\(messageError)"
                
            default:
                print("Have you done something new?")
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


