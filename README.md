# logi-ops

logi-ops is a user-friendly tool for logistics managers who are responsible for decision makings for cargoes allocations to transportations and storages.

Users can register cargoes information such as type of items, size, delivery/expiriy date, transportations in their hand like ships and trcks, including their costs per ton, and storage capacities and costs. 
As you can see even by far, there are many variables in one string of logistics chain. 
It would expectedly be a lot more complicated as the numbers of chains grow and users have more optionalities.

logi-ops is to serve for such needs by handy application with the following key functions

##
### Key Functions
The following functions will be deployed. Users can use this app in the numbered sequence: register logistics elements, manage database, and run the optimization algorithm 

#### 1) Registration 
   This function is the data entry point for users to create the database. Users can input the following information:
   - Ship Information (Name, Size, ETA, Cost, Status)
   - Truck Information (Name, Size, ETA, Cost, Status)
   - Cargo Information (Item, Size, Cost, Expiry, Status)
   - Storage Information (Name, Capacity, ETR, Cost, Acceptable Items, Status)

#### 2) Database Search
   This function is to manage/view users' database. Users can set query criteria, retrieve relevant dataset, and view/modify it
   - Ship Information (By Name, Size, ETA, Cost, Availability)
   - Truck Information (By Name, Size, ETA, Cost, Availability)
   - Cargo Information (By Item, Size, Cost, Expiry, Availability)
   - Storage Information (By Name, Capacity, ETR, Cost, Acceptable Items, Availability)

####  3) Logistics optimizer
   This function optimizes logistics combinations of Ships, Trucks, Cargoes, and Storages. Users can calibrate the whole open-logistics positions, or 
   just some part of the positions, like linking only ships and cargoes that are available and need to be sent within this months.
   
   - Whole logistics Optimizer
   - Cargoes - Ships Optimizer
   - Cargoes/Ships - Storage Optimizer
   - Cargoes/Storage - Truck Optimizer

